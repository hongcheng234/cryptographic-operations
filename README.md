# cryptographic-operations
#Elliptic Curve Cryptography
##This repository contains a set of tools and scripts useful to learn the basics about Elliptic Curve Cryptography.
##They were created for the series for the series of posts entitled  http://andrea.corbellini.name/2015/05/17/elliptic-curve-cryptography-a-gentle-introduction/

# Paillier Encryption Algorithm
  Paillier encryption algorithm has semantic security, that is, given plaintext m1, m2, there is no polynomial timen algorithm to distinguish E(m1),E(m2).
  
## The properties of the Paillier cryptosystem are shown below:
1) Decrypting the product of two ciphertexts obtain the sum of two corresponding plaintexts.
2) Decrypting the plaintext power of a ciphertext obtains the product of two corresponding plaintexts.

## Algorithm description
###Public/private keys generation:
Given two large primes p, q and obtain their product n=pq and lambda=lcm(p-1, q-1). Then, to select the random number g and 
make the equation gcd(L(g^lambda mod N^2), N) = 1 true.


###Encryption:
   A message m is encrypted as ciphertext c=E(m mod N, r mod N) = g^m * r^N mod N^2.

###Decryption:
   The plaintext m is decrypted by calculating the equation D(c) = L(c^lambda mod N^2)/ L(g^lambda mod N^2) mod N.

   
