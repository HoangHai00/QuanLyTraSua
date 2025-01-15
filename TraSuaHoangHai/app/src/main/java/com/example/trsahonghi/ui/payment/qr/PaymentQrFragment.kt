package com.example.trsahonghi.ui.payment.qr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.repository.food.FoodRepositoryImpl
import com.example.trsahonghi.api.repository.vietqr.VietQRRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.base.OrderEnabledLocalBroadcastManager
import com.example.trsahonghi.databinding.FragmentPaymentQrBinding
import com.example.trsahonghi.util.CommonToast
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.ScreenshotUtils
import com.example.trsahonghi.util.StringUtils
import com.example.trsahonghi.widget.dialog.AlertDialogListener
import java.util.ArrayList

class PaymentQrFragment :
    BaseDataBindFragment<FragmentPaymentQrBinding, PaymentQrContract.Presenter>(),
    PaymentQrContract.View {
    companion object {
        private const val REQUEST_WRITE_STORAGE = 100
        private const val ARG_PHONE_NUMBER = "ARG_PHONE_NUMBER"
        private const val ARG_ADDRESS = "ARG_ADDRESS"
        private const val ARG_ID_VOUCHER = "ARG_ID_VOUCHER"
        private const val ARG_LIST_ITEM = "ARG_LIST_ITEM"
        private const val ARG_PRICE = "ARG_PRICE"
        fun newInstance(
            phoneNumber: String?,
            address: String?,
            idVoucher: String?,
            listItem: List<Item>?,
            price: String?
        ): PaymentQrFragment {
            val args = Bundle().apply {

                putString(ARG_PHONE_NUMBER, phoneNumber)
                putString(ARG_ADDRESS, address)
                putString(ARG_ID_VOUCHER, idVoucher)
                putParcelableArrayList(ARG_LIST_ITEM, ArrayList(listItem))
                putString(ARG_PRICE, price)
            }

            val fragment = PaymentQrFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun getLayoutId() = R.layout.fragment_payment_qr

    override fun initView() {
        mBinding?.apply {
            toolbar.setOnBackClickListener {
                backClick()
            }
            btnContinue.setOnClickListener {
                presenter?.submitOrder()
            }
            btnSaveQR.setOnClickListener {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_WRITE_STORAGE
                        )
                        return@setOnClickListener // Chờ quyền được cấp, không thực hiện lưu
                    }
                }

                // Thực hiện lưu khi đã có quyền
                saveQrCode()
            }


        }

    }


    override fun initData() {
        val phoneNumber = arguments?.getString(ARG_PHONE_NUMBER)
        val address = arguments?.getString(ARG_ADDRESS)
        val idVoucher = arguments?.getString(ARG_ID_VOUCHER)
        val listItem = arguments?.getParcelableArrayList<Item>(ARG_LIST_ITEM)
        val price = arguments?.getString(ARG_PRICE)
        mPresenter = PaymentQrPresenter(
            this,
            phoneNumber,
            address,
            idVoucher,
            listItem,
            price,
            VietQRRepositoryImpl(),
            FoodRepositoryImpl()
        ).apply {
            mBinding?.presenter = this
            getQrCode()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp
                CommonToast.showToast(
                    requireContext(),
                    "Đã cấp quyền lưu trữ!",
                    R.drawable.ic_checked_green
                )
            } else {
                // Quyền bị từ chối
                CommonToast.showToast(
                    requireContext(),
                    "Quyền lưu trữ bị từ chối!",
                    R.drawable.ic_check_box_red
                )
            }
        }
    }

    private fun saveQrCode() {
        hideViewToSaveAndShare()
        mBinding?.ctrlQR?.post {
            val filePath = ScreenshotUtils.saveViewAsImage(requireContext(), mBinding?.ctrlQR!!)
            visibleViewAfterSaveAndShare()

            if (filePath != null) {
                CommonToast.showToast(
                    requireContext(),
                    getString(R.string.save_image_success), R.drawable.ic_checked_green
                )
            } else {
                CommonToast.showToast(
                    requireContext(),
                    getString(R.string.save_image_failed), R.drawable.ic_check_box_red
                )
            }
        }
    }

    override fun showDiaLog(message: String) {
        getBaseActivity().showAlertDialogNew(
            icon = null,
            title = getString(R.string.app_notify_title),
            message = message,
            textTopButton = getString(R.string.common_close),
            isCancelable = false,
            listener =
            object : AlertDialogListener {
                override fun onAccept() {
                    getBaseActivity().onBackAllFragments()
                }

                override fun onCancel() {

                }
            }
        )
    }

    override fun setImageQr(bitmap: Bitmap) {
        mBinding?.qrImageView?.setImageBitmap(bitmap)
    }

    override fun showError(message: String) {

    }

    private fun backClick() {
        getBaseActivity().onBackFragment()
    }

    private fun hideViewToSaveAndShare() {
        mBinding?.apply {
            toolbar.visibility = View.INVISIBLE
            btnSaveQR.visibility = View.INVISIBLE
            btnContinue.visibility = View.INVISIBLE
        }
    }

    private fun visibleViewAfterSaveAndShare() {
        mBinding?.apply {
            toolbar.visibility = View.VISIBLE
            btnSaveQR.visibility = View.VISIBLE
            btnContinue.visibility = View.VISIBLE
        }
    }

    override fun sendListFoodBroadcast(list: List<BubbleTea>) {

        val broadcastIntent = Intent(Constants.Actions.NOTIFY_LIST_FOOD).apply {
            putExtra(
                Constants.BundleConstants.LIST_FOOD_CART,
                StringUtils.objectToString(list)
            )
        }
        context?.let { context ->
            OrderEnabledLocalBroadcastManager.getInstance(context)
                .sendBroadcast(broadcastIntent)
        }
        showDiaLog(getString(R.string.order_success))
    }


    override fun getStringRes(id: Int): String {
        return getString(id)
    }
}