;<Program title>

JMP Start			; Jump to START

Start: LHLD 2200H	; Load HL direct to 22H and 00H respectively
	   XCHG			; Exchange contents of DE with contents of HL
       LHLD 2210H	; Load HL direct to 22H and 00H respectively
       DAD D		; contents of HL = contents of HL + contents of DE
       MVI B,00H	; Store 00H register B
       JNC Loop		; If no carry, enter the LOOP
       INR B		; Increase the content of register B by 1
       
Loop:  SHLD 2220H	; Store HL direct to 22H and 20H respectively
	   MOV A,B		; Copy the content of register B to accumulator
	   
       STA 2223H	; Store contents of accumulator to memory location 2223H

HLT					; Terminates the program
