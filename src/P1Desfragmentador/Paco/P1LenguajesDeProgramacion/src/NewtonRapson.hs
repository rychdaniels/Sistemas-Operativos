f x = 2*x^2 + 3*x
fd x = 4*x + 3
newtonRapson xi = if(abs(f xi) < 0.000000000000001)
                    then
                        xi
                    else    
                        newtonRapson (xi - ( (f xi)/(fd xi)) )