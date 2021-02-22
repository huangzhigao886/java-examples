package com.algDemo;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/27
 * @Description:
 */
public class Demo1 {
    public static void main(String[] args) {
        DenseMatrix64F L = new DenseMatrix64F(2,3);
        DenseMatrix64F R= new DenseMatrix64F(3,2);
        DenseMatrix64F RES = new DenseMatrix64F(2,2);
        L.set(0,0,1);
        L.set(0,1,2);
        L.set(0,2,3);
        L.set(1,0,2);
        L.set(1,1,1);
        L.set(1,2,3);


        R.set(0,0,2);
        R.set(0,1,3);
        R.set(1,0,4);
        R.set(1,1,1);
        R.set(2,0,2);
        R.set(2,1,5);

        CommonOps.mult(L,R,RES);

        System.out.println("矩阵的乘法为:"+RES);

    }
}
