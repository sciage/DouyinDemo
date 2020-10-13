package com.example.administrator.douyin;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EventLogger;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private int[] imgs = {R.mipmap.img_video_1, R.mipmap.img_video_2, R.mipmap.img_video_3, R.mipmap.img_video_4};
    private String[] videos = {"https://storage.googleapis.com/misco-storage/uploads/videos/1602336098026_output_compose_video.mp4",
                            "https://storage.googleapis.com/misco-storage/uploads/videos/1602336098026_output_compose_video.mp4",
            "https://storage.googleapis.com/misco-storage/uploads/videos/1602336098026_output_compose_video.mp4",
                            "https://storage.googleapis.com/misco-storage/uploads/videos/1602336098026_output_compose_video.mp4" };

//    private int[] videos = {R.raw.video_1, R.raw.video_2, R.raw.video_3, R.raw.video_4, R.raw.video_5, R.raw.video_6, R.raw.video_7, R.raw.video_8};
    private int index = 0;
    private Context mContext;
//    SimpleExoPlayer player;
    public MyAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.img_thumb.setImageResource(imgs[position]);
//        holder.videoView.setVideoURI(Uri.parse(videos[index]));
        playVideo(holder.videoView, videos[position]);
        holder.videoView.setMediaController(null);
//        index++;
//        if (index >= 7) {
//            index = 0;
//        }


    }

    private void playVideo(final VideoView videoView, String videoPath)
    {
        try
        {
//            mContext.getApplicationContext().getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(mContext);
            mediaController.setAnchorView(videoView);

            Uri video = Uri.parse(videoPath );
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {

                public void onPrepared(MediaPlayer mp)
                {
//                    progressDialog.dismiss();
                    videoView.start();
                }
            });


        }
        catch(Exception e)
        {
//            progressDialog.dismiss();
            System.out.println("Video Play Error :"+e.toString());
//            finish();
        }


}

    @Override
    public int getItemCount() {
        return videos.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumb;
//        StyledPlayerView videoView;
//        private SimpleExoPlayer videoPlayer;

        FullWindowVideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);


        }
    }
}
