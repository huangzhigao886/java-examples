package com.algDemo;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/27
 * @Description: 多元线性最小二乘法  参数 = (XT*x)-1* XT*Y
 * x矩阵的转置乘以x矩阵，求出对应的逆矩阵，然后继续乘以X的转置矩阵，在乘以Y矩阵
 */
public class MulLineRegression {
    public static void main(String[] args) {
        //矩阵X 2个样本，2个特征值
        DenseMatrix64F X = new DenseMatrix64F(2, 3);
        X.set(0, 0, 1);
        X.set(0, 1, 2);
        X.set(0, 2, 3);
        X.set(1, 0, 1);
        X.set(1, 1, 1);
        X.set(1, 2, 3);
        //矩阵X的转置
        DenseMatrix64F XT = new DenseMatrix64F(3, 2);
        CommonOps.transpose(X, XT);
        System.out.println("转置后的结果:" + XT);


        //矩阵X*XT的逆矩阵
        DenseMatrix64F tmp = new DenseMatrix64F(3, 3);
        CommonOps.mult(XT, X, tmp);
        System.out.println("两个矩阵相乘的结果:" + tmp);

        DenseMatrix64F invert = new DenseMatrix64F(3, 3);
        CommonOps.invert(tmp, invert);
        System.out.println("逆矩阵的值为:" + invert);

        //逆矩阵*XT
        DenseMatrix64F tmp1 = new DenseMatrix64F(3, 2);
        CommonOps.mult(invert, XT, tmp1);
        System.out.println("逆矩阵*XT的结果为:" + tmp1);

        //矩阵Y

        DenseMatrix64F Y = new DenseMatrix64F(2, 1);
        Y.set(0,0,5);
        Y.set(1,0,6);
        System.out.println("矩阵Y："+Y);

        //结果,3,1
        DenseMatrix64F B = new DenseMatrix64F(3, 1);

        CommonOps.mult(tmp1, Y, B);
        System.out.println("参数集:" + B);

        for(double d:B.getData()){
            System.out.println(d);
        }

    }
}
