package com.example.dragandscale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.dragandscale.views.RectView;

public class MainActivity extends AppCompatActivity {

    private RectView r;
    private ConstraintLayout layout;

    private float xT;
    private float yT;
    private int xMove;
    private int yMove;
    private int id=0;
    private int xFixed;
    private int yFixed;

    private ConstraintLayout.LayoutParams l;

    private int con=100;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r=findViewById(R.id.rectView);
        layout=findViewById(R.id.layout);

        Toast.makeText(this,"Drag the corners or sides to resize, drag the center to move",Toast.LENGTH_LONG).show();

        r.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        id=0;
                        xT=motionEvent.getRawX();
                        yT=motionEvent.getRawY()-284;
                        if((xT>Points.a.x-con&&xT<Points.a.x+con)&&(yT>Points.a.y-con&&yT<Points.a.y+con)){
                            id=1;
                            xFixed= (int) Points.c.x;
                            yFixed= (int) Points.c.y;
                        }
                        else if((xT>Points.b.x-con&&xT<Points.b.x+con)&&(yT>Points.b.y-con&&yT<Points.b.y+con)){
                            id=2;
                            xFixed= (int) Points.d.x;
                            yFixed= (int) Points.d.y;
                        }
                        else if((xT>Points.c.x-con&&xT<Points.c.x+con)&&(yT>Points.c.y-con&&yT<Points.c.y+con)){
                            id=3;
                            xFixed= (int) Points.a.x;
                            yFixed= (int) Points.a.y;
                        }
                        else if((xT>Points.d.x-con&&xT<Points.d.x+con)&&(yT>Points.d.y-con&&yT<Points.d.y+con)){
                            id=4;
                            xFixed= (int) Points.b.x;
                            yFixed= (int) Points.b.y;
                        }
                        else if((xT>Points.a.x-con&&xT<Points.a.x+con)&&(yT>Points.a.y+con&&yT<Points.d.y-con)){
                            id=5;
                            xFixed= (int) Points.b.x;
                        }
                        else if((yT>Points.a.y-con&&yT<Points.a.y+con)&&(xT>Points.a.x+con&&yT<Points.b.x-con)){
                            id=6;
                            yFixed= (int) Points.c.y;
                        }
                        else if((xT>Points.c.x-con&&xT<Points.c.x+con)&&(yT>Points.b.y+con&&yT<Points.c.y-con)){
                            id=7;
                            xFixed= (int) Points.a.x;
                        }
                        else if((yT>Points.c.y-con&&yT<Points.c.y+con)&&(xT>Points.a.x+con&&xT<Points.b.x-con)){
                            id=8;
                            yFixed= (int) Points.b.y;
                        }
                        else{
                            ClipData a =ClipData.newPlainText("","");
                            View.DragShadowBuilder shadowBuilder=new View.DragShadowBuilder(view);
                            view.startDrag(a,shadowBuilder,view,0);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        xMove= (int) motionEvent.getRawX();
                        yMove= (int) (motionEvent.getRawY()-284);

                        switch (id){
                            case 1:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xFixed-xMove);
                                l.height= (yFixed-yMove);
                                view.setX(xMove);
                                view.setY(yMove);
                                r.setLayoutParams(l);
                                break;

                            case 2:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xMove-xFixed);
                                l.height= (yFixed-yMove);
                                view.setX(xMove-l.width);
                                view.setY(yMove);
                                r.setLayoutParams(l);
                                break;

                            case 3:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xMove-xFixed);
                                l.height= (yMove-yFixed);
                                view.setX(xMove-l.width);
                                view.setY(yMove-l.height);
                                r.setLayoutParams(l);
                                break;

                            case 4:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xFixed-xMove);
                                l.height= (yMove-yFixed);
                                view.setX(xMove);
                                view.setY(yMove-l.height);
                                r.setLayoutParams(l);
                                break;

                            case 5:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xFixed-xMove);
                                view.setX(xMove);
                                r.setLayoutParams(l);
                                break;
                            case 6:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.height= (yFixed-yMove);
                                view.setY(yMove);
                                r.setLayoutParams(l);
                                break;
                            case 7:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.width= (xMove-xFixed);
                                view.setX(xFixed);
                                r.setLayoutParams(l);
                                break;
                            case 8:
                                l= (ConstraintLayout.LayoutParams) r.getLayoutParams();
                                l.height= (yMove-yFixed);
                                view.setY(yFixed);
                                r.setLayoutParams(l);
                                break;
                        }
                        break;
                }
                return true;
            }
        });

        layout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                    case DragEvent.ACTION_DROP:
                        view = (RectView) dragEvent.getLocalState();
                        dragEvent.getX();
                        dragEvent.getY();
                        view.animate()
                                .x((float) (dragEvent.getX()-view.getWidth()/2.0))
                                .y((float) (dragEvent.getY()-view.getHeight()/2.0))
                                .setDuration(500)
                                .start();
                        return true;
                }
                return false;
            }
        });
    }

}