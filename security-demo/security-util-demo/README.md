# Security Util Demo

 ## java.security.InvalidKeyException

```
Exception in thread "main" java.security.InvalidKeyException: Invalid AES key length: 2 bytes
	at java.base/com.sun.crypto.provider.AESCrypt.init(AESCrypt.java:90)
	at java.base/com.sun.crypto.provider.ElectronicCodeBook.init(ElectronicCodeBook.java:97)
	at java.base/com.sun.crypto.provider.CipherCore.init(CipherCore.java:591)
	at java.base/com.sun.crypto.provider.CipherCore.init(CipherCore.java:467)
	at java.base/com.sun.crypto.provider.AESCipher.engineInit(AESCipher.java:313)
	at java.base/javax.crypto.Cipher.implInit(Cipher.java:839)
	at java.base/javax.crypto.Cipher.chooseProvider(Cipher.java:901)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1286)
	at java.base/javax.crypto.Cipher.init(Cipher.java:1223)
	at huaminglin.demo.security.util.AesDemo.encrypt(AesDemo.java:22)
	at huaminglin.demo.security.util.AesDemo.main(AesDemo.java:42)
```

## JCE jurisdiction policy files

Returns the maximum key length for the specified transformation according to the installed JCE jurisdiction policy files. 

sudo docker run --rm -v $PWD/target/classes:/classes openjdk:8u265-jdk java -classpath /classes huaminglin.demo.security.util.AesDemo

sudo docker run --rm -it openjdk:8u265-jdk bash

```
find / -iname "*.jar" | grep policy
/usr/local/openjdk-8/jre/lib/security/policy/limited/US_export_policy.jar
/usr/local/openjdk-8/jre/lib/security/policy/limited/local_policy.jar
/usr/local/openjdk-8/jre/lib/security/policy/unlimited/US_export_policy.jar
/usr/local/openjdk-8/jre/lib/security/policy/unlimited/local_policy.jar
```
