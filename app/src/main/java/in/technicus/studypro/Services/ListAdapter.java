package in.technicus.studypro.Services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.technicus.studypro.R;

public class ListAdapter extends ArrayAdapter<ListPojo> {
    Context context;
    public ListAdapter(Context context, int resourceId,List<ListPojo> items){
        super(context,resourceId,items);
        this.context = context;
    }
    public class ViewHolder{
        TextView agendaTitle,aImportance, hiddenId;
    }
    public View getView(int position, View ConvertView,ViewGroup parent){
        ViewHolder viewHolder;
        ListPojo listPojo = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(ConvertView == null){
            viewHolder = new ViewHolder();
            ConvertView = layoutInflater.inflate(R.layout.custom_list, null);
            viewHolder.agendaTitle = (TextView) ConvertView.findViewById(R.id.agendaTitle);
            viewHolder.aImportance = (TextView) ConvertView.findViewById(R.id.Importance);
            viewHolder.hiddenId = (TextView) ConvertView.findViewById(R.id.hidden_id);
            ConvertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) ConvertView.getTag();
        }
        viewHolder.agendaTitle.setText(listPojo.getAgendaTitle());
        viewHolder.aImportance.setText(listPojo.getAgendaImportance());
        viewHolder.hiddenId.setText(listPojo.getAgendaId());
        return ConvertView;
    }
}
