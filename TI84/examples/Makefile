FILE=tios
ASM=sdasz80
CC=sdcc

default:${FILE}.c
	${ASM} -p -g -o ${FILE}_crt0.rel ${FILE}_crt0.s 
	${CC} --no-std-crt0 --code-loc 40347 --data-loc 0 --std-sdcc99 -mz80 --reserve-regs-iy -o ${FILE}.ihx ${FILE}_crt0.rel ${FILE}.c \
		&& hex2bin ${FILE}.ihx \
			&& ../binpac8x.py -O ${CC} ${FILE}.bin ${FILE}.8xp \
				&& make clean
clean:
	rm -rf *.ihx *.rel *.bin *.hex *.asm *.lst *.noi *.map *.sym *.lk **~
