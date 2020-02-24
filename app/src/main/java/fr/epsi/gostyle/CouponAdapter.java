package fr.epsi.gostyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.epsi.gostyle.modele.Coupon;

class CouponAdapter extends ArrayAdapter<Coupon> {
    public CouponAdapter(@NonNull Context context, int resource, @NonNull List<Coupon> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.c_coupon, null);

        TextView textViewCode = convertView.findViewById(R.id.textViewCode);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);

        Coupon coupon=getItem(position);

        textViewCode.setText(coupon.getCode());
        textViewDescription.setText(coupon.getDescription());
        return convertView;
    }
}
