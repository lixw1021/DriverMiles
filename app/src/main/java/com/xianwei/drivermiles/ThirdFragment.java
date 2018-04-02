package com.xianwei.drivermiles;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class ThirdFragment extends Fragment {

    @BindView(R.id.layout_flight)
    LinearLayout layoutFlight;
    @BindView(R.id.layout_flight_up)
    LinearLayout layoutFlightUp;
    //    @BindView(R.id.layout_recyclerView)
//    LinearLayout layoutRecyclerView;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    @BindView(R.id.layout_third_fragment)
    RelativeLayout layoutParent;
    @BindView(R.id.layout_infor)
    LinearLayout layoutInfor;
    @BindView(R.id.text_route)
    TextView routeTV;

    @BindView(R.id.item1)
    LinearLayout item1;
    @BindView(R.id.item2)
    LinearLayout item2;
    @BindView(R.id.item3)
    LinearLayout item3;
    @BindView(R.id.item4)
    LinearLayout item4;
    @BindView(R.id.item5)
    LinearLayout item5;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;

    private CardAdapter cardAdapter;
    private List<RecyclerviewItemModel> listData;
    private static final long ANIMATION_DURATION = 400;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this, view);
        initialdata();
        return view;
    }

    private void initialdata() {
        ((ImageView)item1.findViewById(R.id.item_image)).setImageResource(R.drawable.ic_cloud);
        ((ImageView)item2.findViewById(R.id.item_image)).setImageResource(R.drawable.ic_cloudhot);
        ((ImageView)item3.findViewById(R.id.item_image)).setImageResource(R.drawable.ic_flight_black_24dp);
        ((ImageView)item4.findViewById(R.id.item_image)).setImageResource(R.drawable.ic_access_time_black_24dp);
        ((ImageView)item5.findViewById(R.id.item_image)).setImageResource(R.drawable.ic_flight_black_24dp);

        ((TextView) item1.findViewById(R.id.item_name)).setText("MOSCOW");
        ((TextView) item2.findViewById(R.id.item_name)).setText("SEDUL");
        ((TextView) item3.findViewById(R.id.item_name)).setText("AIRBUS A340");
        ((TextView) item4.findViewById(R.id.item_name)).setText("08H:35M");
        ((TextView) item5.findViewById(R.id.item_name)).setText("KOREAN AIRLINES");

        ((TextView) item1.findViewById(R.id.item_subname)).setText("37 °F");
        ((TextView) item2.findViewById(R.id.item_subname)).setText("57 °F");

    }

    @OnClick(R.id.layout_third_fragment)
    void onTouch() {
        if (routeTV.getVisibility() == VISIBLE) {
            hideInforLayout();
            showList();
            hideRouteTv();
            moveUpFlightLayout();

        } else {
            showInforLayout();
            hideList();
            showRouteTv();
            moveDownFlightLayout();
        }
    }

    private void moveUpFlightLayout() {
        final TranslateAnimation animation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -2.5f);
        animation1.setDuration(ANIMATION_DURATION);
        layoutFlight.animate().setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                layoutFlight.startAnimation(animation1);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutFlight.setVisibility(View.INVISIBLE);
                layoutFlightUp.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).start();

    }

    private void moveDownFlightLayout() {
        final TranslateAnimation animation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 2.5f);
        animation2.setDuration(ANIMATION_DURATION);
        layoutFlightUp.animate().setDuration(ANIMATION_DURATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                layoutFlightUp.startAnimation(animation2);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutFlight.setVisibility(VISIBLE);
                layoutFlightUp.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).start();

    }


    private void showInforLayout() {
        layoutInfor.animate()
                .alpha(1.0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        layoutInfor.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();

    }

    private void hideInforLayout() {
        layoutInfor.animate()
                .alpha(0.0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        layoutInfor.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();

    }

    private void showList() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 500f, 0);
        animation.setDuration(ANIMATION_DURATION);
        item1.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item1.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item1.startAnimation(animation);


        item2.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item2.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item2.startAnimation(animation);

        item3.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item3.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item3.startAnimation(animation);

        item4.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item4.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item4.startAnimation(animation);


        item5.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item5.setVisibility(VISIBLE);
                layoutBottom.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item5.startAnimation(animation);

        layoutBottom.animate().setDuration(ANIMATION_DURATION).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutBottom.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        layoutBottom.startAnimation(animation);
    }

    private void hideList() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 500f);
        animation.setDuration(ANIMATION_DURATION);

        item1.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item1.startAnimation(animation);
//        item2.setVisibility(View.INVISIBLE);
        item2.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item2.startAnimation(animation);
//        item3.setVisibility(View.INVISIBLE);

        item3.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item3.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item3.startAnimation(animation);
//        item4.setVisibility(View.INVISIBLE);
        item4.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item4.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item4.startAnimation(animation);
//        item5.setVisibility(View.INVISIBLE);
        item5.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                item5.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        item5.startAnimation(animation);

        layoutBottom.animate().setDuration(ANIMATION_DURATION).alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutBottom.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        layoutBottom.startAnimation(animation);
    }

    private void showRouteTv() {

        routeTV.animate()
                .alpha(1.0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        routeTV.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }

    private void hideRouteTv() {
        routeTV.animate()
                .alpha(0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        routeTV.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }
}
