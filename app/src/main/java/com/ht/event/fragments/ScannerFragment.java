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


import com.google.gson.Gson;
import com.ht.event.R;
import com.ht.event.model.User;
import com.ht.event.scanner.ZBarConstants;
import com.ht.event.scanner.ZBarScannerActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.sourceforge.zbar.Symbol;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button scan,scanAgain;
    private LinearLayout layout;
    private CircleImageView image;
    private TextView name,contact,email,organisation;
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;


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
        name = (TextView)view.findViewById(R.id.userName);
        email = (TextView)view.findViewById(R.id.userEmail);
        contact = (TextView)view.findViewById(R.id.userContact);
        organisation = (TextView)view.findViewById(R.id.userOrg);
        resultLayout = (RelativeLayout)view.findViewById(R.id.resultLayout);
        layout = (LinearLayout)view.findViewById(R.id.layout);
        image = (CircleImageView)view.findViewById(R.id.circleImageView);

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
        String resultStr;
        Gson gson = new Gson();
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:


                if (resultCode == getActivity().RESULT_OK) {
                        layout.setVisibility(View.GONE);
                    resultStr = data.getStringExtra(ZBarConstants.SCAN_RESULT);
                    User user = gson.fromJson(resultStr,User.class);
                    name.setText(user.getName());
                    email.setText(user.getEmail());
                    contact.setText(user.getPhoneNo());
                    organisation.setText(user.getOrganisation());
                    resultLayout.setVisibility(View.VISIBLE);
                    scanAgain.setOnClickListener(this);

                    //Image loader
                    String imageUrl=user.getImage();
                    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).build();
                    ImageLoader imgLoader = ImageLoader.getInstance();
                    imgLoader.init(config);
                    imgLoader.displayImage(imageUrl, image);


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

