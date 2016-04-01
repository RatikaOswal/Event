package com.ht.event.fragments;


import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ht.event.R;
import com.ht.event.scanner.ZBarConstants;
import com.ht.event.scanner.ZBarScannerActivity;

import net.sourceforge.zbar.Symbol;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button scan,scanAgain;
    private LinearLayout layout;
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private TextView result;
    private RelativeLayout resultLayout;



    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scanner, container, false);
        scan = (Button)view.findViewById(R.id.qrscan_btn);
        scanAgain = (Button)view.findViewById(R.id.qrscan_btn_again);
        resultLayout = (RelativeLayout)view.findViewById(R.id.resultLayout);
        result = (TextView)view.findViewById(R.id.result);
        layout = (LinearLayout)view.findViewById(R.id.layout);
        scan.setOnClickListener(this);
        scanAgain.setOnClickListener(this);


        return view;

    }

    public boolean isCameraAvailable() {
        PackageManager pm = getActivity().getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:

                if (resultCode == getActivity().RESULT_OK) {
                        layout.setVisibility(View.GONE);
                    resultLayout.setVisibility(View.VISIBLE);
                    result.setText(data.getStringExtra(ZBarConstants.SCAN_RESULT));
                    scanAgain.setOnClickListener(this);
                  //  Toast.makeText(getActivity(), "Scan Result = "+ data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
                } else if(resultCode == getActivity().RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);

                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.qrscan_btn:
                if (isCameraAvailable()) {
                    Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
                    intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
                    startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
                } else {
                    Toast.makeText(getActivity(), "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.qrscan_btn_again:
                if (isCameraAvailable()) {
                    Intent intent = new Intent(getActivity(), ZBarScannerActivity.class);
                    intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
                    startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
                } else {
                    Toast.makeText(getActivity(), "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
                }
                break;



        }

    }
}

