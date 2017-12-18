package chingtech.library.anim;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package chingtech.library.widget
 * Description: 3D翻转效果
 * Created by 师春雷
 * Created at 17/12/16 下午6:09
 *
 * 源码：https://github.com/jiangzepeng/Rotate3DSample
 */
public class Rotate3D {

    private Context context;
    private View    parentView;
    private View    positiveView;
    private View    negativeView;

    private int centerX;
    private int centerY;
    private int depthZ;
    private int duration;

    private Rotate3dAnimation openAnimation;
    private Rotate3dAnimation closeAnimation;

    private boolean isOpen = false;

    private Rotate3D(Builder builder) {
        this.context = builder.context;
        this.parentView = builder.parentView;
        this.positiveView = builder.positiveView;
        this.negativeView = builder.negativeView;
        this.duration = builder.duration;
        this.depthZ = builder.depthZ;
    }

    public View getParentView() {
        return parentView;
    }

    public View getPositiveView() {
        return positiveView;
    }

    public View getNegativeView() {
        return negativeView;
    }

    public void transform() {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = parentView.getWidth() / 2;
        centerY = parentView.getHeight() / 2;
        if (openAnimation == null) {
            initOpenAnim();
            initCloseAnim();
        }

        //用作判断当前点击事件发生时动画是否正在执行
        if (openAnimation.hasStarted() && !openAnimation.hasEnded()) {
            return;
        }
        if (closeAnimation.hasStarted() && !closeAnimation.hasEnded()) {
            return;
        }

        //判断动画执行
        parentView.startAnimation(isOpen ? closeAnimation : openAnimation);

        isOpen = !isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
     * 卡牌文本介绍打开效果：注意旋转角度
     */
    private void initOpenAnim() {
        //从0到90度，顺时针旋转视图，此时reverse参数为true，达到90度时动画结束时视图变得不可见，
        openAnimation = new Rotate3dAnimation(context, 0, 90, centerX, centerY, depthZ, true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setInterpolator(new AccelerateInterpolator());
        openAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                positiveView.setVisibility(View.GONE);
                negativeView.setVisibility(View.VISIBLE);

                //从270到360度，顺时针旋转视图，此时reverse参数为false，达到360度动画结束时视图变得可见
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(context, 270, 360,
                                                                          centerX, centerY, depthZ,
                                                                          false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                parentView.startAnimation(rotateAnimation);
            }
        });
    }

    /**
     * 卡牌文本介绍关闭效果：旋转角度与打开时逆行即可
     */
    private void initCloseAnim() {
        closeAnimation = new Rotate3dAnimation(context, 360, 270, centerX, centerY, depthZ, true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setInterpolator(new AccelerateInterpolator());
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                positiveView.setVisibility(View.VISIBLE);
                negativeView.setVisibility(View.GONE);

                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(context, 90, 0, centerX,
                                                                          centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                parentView.startAnimation(rotateAnimation);
            }
        });
    }

    public static class Builder {
        private int depthZ   = 400;
        private int duration = 400;
        private Context context;
        private View    parentView;
        private View    positiveView;
        private View    negativeView;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDepthZ(int depthZ) {
            this.depthZ = depthZ;
            return this;
        }

        public Builder bindParentView(View parentView) {
            this.parentView = parentView;
            return this;
        }

        public Builder bindPositiveView(View positiveView) {
            this.positiveView = positiveView;
            return this;
        }

        public Builder bindNegativeView(View negativeView) {
            this.negativeView = negativeView;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Rotate3D create() {
            Rotate3D rotate3D = new Rotate3D(this);
            if (rotate3D.getParentView() == null) {
                throw new NullPointerException("Please set ParentView");
            }
            if (rotate3D.getPositiveView() == null) {
                throw new NullPointerException("Please set PositiveView");
            }
            if (rotate3D.getNegativeView() == null) {
                throw new NullPointerException("Please set NegativeView");
            }

            return rotate3D;
        }
    }

    public class Rotate3dAnimation extends Animation {
        private final float   mFromDegrees;
        private final float   mToDegrees;
        private final float   mCenterX;
        private final float   mCenterY;
        private final float   mDepthZ;
        private final boolean mReverse;
        private       Camera  mCamera;
        float scale = 1;    // 像素密度

        /**
         * 创建一个绕 y 轴旋转的3D动画效果，旋转过程中具有深度调节，可以指定旋转中心。
         *
         * @param context     上下文,用于获取像素密度
         * @param fromDegrees 起始时角度
         * @param toDegrees   结束时角度
         * @param centerX     旋转中心x坐标
         * @param centerY     旋转中心y坐标
         * @param depthZ      最远到达的z轴坐标
         * @param reverse     true 表示由从0到depthZ，false相反
         */
        public Rotate3dAnimation(Context context, float fromDegrees, float toDegrees, float centerX,
                float centerY, float depthZ, boolean reverse) {
            mFromDegrees = fromDegrees;
            mToDegrees = toDegrees;
            mCenterX = centerX;
            mCenterY = centerY;
            mDepthZ = depthZ;
            mReverse = reverse;
            // 获取手机像素密度 （即dp与px的比例）
            scale = context.getResources().getDisplayMetrics().density;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float fromDegrees = mFromDegrees;
            float       degrees     = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

            final float  centerX = mCenterX;
            final float  centerY = mCenterY;
            final Camera camera  = mCamera;
            final Matrix matrix  = t.getMatrix();

            camera.save();
            // 调节深度
            if (mReverse) {
                camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
            } else {
                camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
            }
            // 绕y轴旋转
            camera.rotateY(degrees);
            camera.getMatrix(matrix);
            camera.restore();

            // 修正失真
            float[] mValues = new float[9];
            matrix.getValues(mValues);                //获取数值
            mValues[6] = mValues[6] / scale;            //数值修正
            mValues[7] = mValues[7] / scale;            //数值修正
            matrix.setValues(mValues);                //重新赋值

            // 调节中心点，旋转中心默认是坐标原点，对于图片来说就是左上角位置。
            matrix.preTranslate(-centerX, -centerY); // 使用pre将旋转中心移动到和Camera位置相同
            matrix.postTranslate(centerX, centerY);  // 使用post将图片(View)移动到原来的位置
        }
    }
}
