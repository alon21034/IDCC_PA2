package idcc.ntu.idcc_pa2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

public class ContentDialogFragment extends DialogFragment implements OnClickListener {

    private String title;
    private String content;
    
    public static DialogFragment newInstance(String title, String content) {
        DialogFragment fragment = new ContentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            content = bundle.getString("content");
        }
    }
    
    private View inflateView() {
        View view = View.inflate(getActivity(), R.layout.dialog_fragment_content, null);
        
        TextView textview = (TextView) view.findViewById(R.id.content_dialog_textview);
        textview.setText(content);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(inflateView())
                .setNegativeButton("OK", this);
        return builder.create();
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
}