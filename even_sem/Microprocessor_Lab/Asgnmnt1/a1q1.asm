;<Program title>

JMP Start		; Jump to START

Start: LXI H,2200H	; Store 22H to H register and 00H to L register
       MOV A,M	; Copy contents of Location 2200H to accumulator
       INX H		; Increase the address of H-L register pair by 1
       MVI D,00H	; Store 00H to register D
       ADD M		; Add content of accumulator to contents of memory location from HL
       JNC Loop	; If no carry, enter the LOOP
       INR D		; Increase the content of register D by 1
       
Loop:  STA 2203H	; Store contents of accumulator to memory location 2203H
	MOV A,D	; Copy contents of register D to accumulator
	STA 2202H	; Store contents of accumulator to memory location 2202H
       HLT		; Terminates the program
