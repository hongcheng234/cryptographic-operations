package com.ecsda.java;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 椭圆曲线签名算法
 * 
 * 速度快 强度高 签名短
 * 
 */
public class Ecdsa {
	 private static final String SIGNALGORITHMS = "SHA256withECDSA";
	 private static final String ALGORITHM = "EC";
	 private String data;
	 
	 public Ecdsa(String aData) {
		 data = aData;
	 }
	/**
 	* 加签
 	* @param privateKey 私钥
 	* @param data 数据 
 	* @return
 	*/
    public String signECDSA(PrivateKey privateKey) {
        String result = "";
        try {
            //执行签名
            Signature signature = Signature.getInstance(SIGNALGORITHMS);
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            byte[] sign = signature.sign();
            return HexUtil.encodeHexString(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * 验签
     * @param publicKey 公钥
     * @param signed 签名
     * @param data 数据
     * @return
     */
    public boolean verifyECDSA(PublicKey publicKey, String signed, String vData) {
        try {
            //验证签名
            Signature signature = Signature.getInstance(SIGNALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(vData.getBytes());
            byte[] hex = HexUtil.decode(signed);
            boolean bool = signature.verify(hex);
           // System.out.println("验证：" + bool);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
   /**
    * @param key 私钥的字符串
    * @return
    * @throws Exception
    */
    public PrivateKey getPrivateKey(PrivateKey key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
 
    /**
     * @param key 公钥的字符串
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(PublicKey key) throws Exception {	
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }
 
 
    
 
    /**
     * 生成密钥对
     * @return
     * @throws Exception
     */
    public KeyPair getKeyPair() throws Exception {
        KeyPairGenerator kf = KeyPairGenerator.getInstance(ALGORITHM);
        kf.initialize(256);
        KeyPair keyPair = kf.generateKeyPair();
        return keyPair;
    }
}