checksum l = ck 0 l
ck suma [] = suma;
ck suma (x:xs) = ck (mod((suma+x)*113)10000007) xs