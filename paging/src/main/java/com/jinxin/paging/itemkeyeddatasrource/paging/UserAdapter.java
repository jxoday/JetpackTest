package com.jinxin.paging.itemkeyeddatasrource.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jinxin.paging.R;
import com.jinxin.paging.pagekeyeddatasource.model.User;
import com.squareup.picasso.Picasso;

/**
 * @author JinXin 2020/10/11
 */
public class UserAdapter  extends PagedListAdapter<User, UserAdapter.UserViewHolder> {

    private static final String TAG = "MovieAdapter";

    public UserAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<User> diffCallback = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = getItem(position);

        if (user == null) {
            holder.ivAvatar.setImageResource(R.drawable.treasure_8k);
            holder.tvName.setText("");
        } else {
            Picasso.get()
                    .load(user.avatar)
                    .placeholder(R.drawable.invitation_8k)
                    .error(R.drawable.treasure_8k)
                    .into(holder.ivAvatar);

            holder.tvName.setText(user.name);

        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
