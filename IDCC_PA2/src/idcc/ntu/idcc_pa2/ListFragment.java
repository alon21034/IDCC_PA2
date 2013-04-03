package idcc.ntu.idcc_pa2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment implements OnItemClickListener {

    private String[] titles;
    
    private ListView mListView;
    private OnChooseItemListener mListener;
    ArrayAdapter<String> mAdapter;
    
    public static Fragment newInstance(String[] strs) {
        Fragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("key", strs);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            titles = bundle.getStringArray("key");
        }
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MainActivity) activity;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        setTitles(titles);
        return view;
    }
    
    public void setTitles(String[] strs) {
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        if (strs != null)
            mAdapter.addAll(strs);
        mListView.setAdapter(mAdapter);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.onChooseItem(position);
    }
    
    public interface OnChooseItemListener {
        public void onChooseItem(int position);
    }
}
