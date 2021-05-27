package com.syzs.test;

import com.pie.algo.AlgoNative;

public class test2 {
    public static void main(String[] args) {
        System.loadLibrary("AlgoWrapperJni");


        /*//波段合成
        String[] arg = new String[]{"E:\\分类算法\\Landsat7\\LE71320352001330SGS00\\L71132035_03520011126_B40.TIF",
                "E:\\分类算法\\Landsat7\\LE71320352001330SGS00\\L71132035_03520011126_B30.TIF","E:\\分类算法\\Landsat7\\LE71320352001330SGS00\\L71132035_03520011126_B20.TIF"};
        int[][] arg2 = new int[3][1];
        boolean result = AlgoNative.ExcuteAlgoBandCombination3(arg,arg2,0,"E:\\分类算法\\out\\OUT.tif");
        System.out.println(result);*/

      /*  //影像分割
        String pszImgFilename = "E:\\分类算法\\out\\OUT.tif";
        String pszSegFilename = "E:\\分类算法\\out\\OUT.sll";
        int nInitType = 2; //分水岭算法
        float fLevelMinsize = 100 ;
        float shape = (float) 0.3;
        float compact = (float) 0.1;
        float edge = (float) 0.5;
        int feature = 0;
        boolean result = AlgoNative.MSS_Segment(pszImgFilename, pszSegFilename, nInitType,
                5, 10, fLevelMinsize, shape, compact, edge, feature);
        System.out.println(result);*/

        //自动样本选择
        String pszImgFilename ="";
        String pszSegFilename = "";
        String shpFilePath = "";
        String pszDstFilename = "";
        String pszFieldId = "";
        String pszFiledName = "";
        double dfPercent = 30;
        double dfBuffer = 10 ;
        int nMaxCount = 400;
        boolean result = AlgoNative.MSS_Sample(pszImgFilename, pszSegFilename, shpFilePath, pszDstFilename, pszFieldId, pszFiledName, dfPercent, dfBuffer, nMaxCount);


        /*//自动分类
        String pszImgFilename = "E:\\分类算法\\out\\OUT.tif";
        String pszSegFilename = "";
        String pszSamFilename = "";
        String pszDstFilename = "";
        int[] bSpecFeatures = new int[]{};
        int[] bTextFeatures = new int[]{};
        int[] bSpatialFeatures = new int[]{};
        int[] bIndexFeatures = new int[]{};
        String[] papszParamters = new String[]{};
        boolean result = AlgoNative.MSS_Classify(pszImgFilename, pszSegFilename, pszSamFilename,
                pszDstFilename, bSpecFeatures, bTextFeatures, bSpatialFeatures, bIndexFeatures, papszParamters);*/
    }
}
