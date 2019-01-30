split :: String -> [String]
split " " = []
split xs = ys : (split . drop 1) zs
   where (ys, zs) = span (/='p') xs
   
word2 l = words l

formaPalabra :: Eq a => a -> [a] -> [ [a] ]
formaPalabra espacio [] = []
formaPalabra espacio texto =  palabra : formaPalabra espacio (drop 1 restoTexto)
                                 where (palabra, restoTexto) = span (/= espacio) texto
   
