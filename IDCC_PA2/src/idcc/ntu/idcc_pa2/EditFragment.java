package idcc.ntu.idcc_pa2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditFragment extends Fragment implements OnClickListener {

    private OnEditListener mListener;
    
    private EditText titleEdittext;
    private EditText contentEdittext;
    
    public static Fragment newInstance() {
        Fragment fragment = new EditFragment();
        
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (titleEdittext != null && contentEdittext != null) {
            titleEdittext.setText("");
            contentEdittext.setText("");
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, null);
        Button btn = (Button) view.findViewById(R.id.save_button);
        btn.setOnClickListener(this);
        
        titleEdittext = (EditText) view.findViewById(R.id.title_edittext);
        contentEdittext = (EditText) view.findViewById(R.id.content_edittext);
        
        return view;
    }
    
    private String getTitle() {
        String str = titleEdittext.getText().toString();
        return (TextUtils.isEmpty(str))? "Title" : str;
    }
    
    private String getContent() {
        String str = contentEdittext.getText().toString();
        return (TextUtils.isEmpty(str))? "Empty Content" : str;
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MainActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.save_button:
            mListener.onSave(getTitle(), getContent());
            break;

        default:
            break;
        }
    }
    
    public interface OnEditListener {
        public void onSave(String title, String content);
    }
}
