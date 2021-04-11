package com.ecsda.java;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Run {
	public static void main(String[] args) throws Exception {
		Ecdsa e = new Ecdsa("hello");
		//生成公钥私钥
		KeyPair keyPair1 = e.getKeyPair();
		PublicKey publicKey1 = keyPair1.getPublic();
		PrivateKey privateKey1 = keyPair1.getPrivate();
		//密钥转16进制字符串
		String publicKey = HexUtil.encodeHexString(publicKey1.getEncoded());
		String privateKey = HexUtil.encodeHexString(privateKey1.getEncoded());
		System.out.println("生成公钥："+publicKey);
		System.out.println("生成私钥："+privateKey);
		//转密钥对象
		PrivateKey privateKey2 = e.getPrivateKey(privateKey1);
		PublicKey publicKey2 = e.getPublicKey(publicKey1);
		//加签验签
		String signECDSA = e.signECDSA(privateKey2);
		boolean verifyECDSA = e.verifyECDSA(publicKey2, signECDSA, "hello");
		System.out.println("验签结果："+verifyECDSA);
	}
}
