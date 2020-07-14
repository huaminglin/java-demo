# Demo binary to text demo

## Binary2TextDemo

```
Qye1P1NGUU4DqM6xeGAnsP/base58(16 bytes):22
wiqleefuQOKxJcbUSO0Cdg==/base64(16 bytes):24
3htr1v3pdZCnS6Po3StBhxjyCVFb/base58(20 bytes):28
wiqleefuQOIAAeJAsSXG1EjtAnY=/base64(20 bytes):28
```

Conclusion: Remove the padding from base64, base64 has higher ration than base58.

## The Base58 Alphabet and Base64 Alphabet

123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz

ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/

Conclusion: base58 follows ASCII alphabet character order, but base64 doesn't.

## Base64 vs. Base58: sort order

base64 does not preserve sort order of the unencoded strings.
From The Base58 Alphabet, it seems base58 preserve sort order of the unencoded strings.

## Base64 vs. Base58: ratio

3htr1v3pdZCnS6Po3StBhxjyCVFb/base58(20 bytes):28

wiqleefuQOIAAeJAsSXG1EjtAnY=/base64(20 bytes):28

From the above encoding result, base64 has higher ratio than base58.

## Base64UrlDemo

base64url

```
getEncoder: K/NPJKesSC+bcFfCHlrWyA==
getUrlEncoder: K_NPJKesSC-bcFfCHlrWyA==
```
