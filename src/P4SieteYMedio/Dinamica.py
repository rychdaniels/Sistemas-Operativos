import os
import sys
import time
def main():
    print("\n\tEjecucion dinamica de procesos")
    print("\tSoy el padre de todos los proceos, mi pid es :",os.getpid())
    pid = os.fork()
    if(pid==0):
        os.execlp("gcc", "gcc","SieteyMedio.c")
        os.exit(0)
    hijo = os.wait()
    os.execlp("./a.exe","a.exe")
main()    
