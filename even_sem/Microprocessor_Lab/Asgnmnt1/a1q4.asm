;<Program title>

JMP Start			 ; Jump to START

Start: LXI H, 2500H	 ; Store 25H to H register and 00H to L register
	   MOV A,M		 ; Copy the content of Memory to accumulator
	   MVI B,08H	 ; Store 08H to register B
	   MVI D,00H	 ; Store 00H register D

LOOP:  RLC			 ; Rotate left accumulator
	   JNC SKIP		 ; If no carry, jump to SKIP
	   INR D		 ; Increase the content of register D by 1

SKIP:  DCR B		 ; Decrease the content of register B by 1
	   JNZ LOOP		 ; If not zero, jump to LOOP
	   MOV A,D		 ;  Copy the content of register D to accumulator
	   STA 2610H	 ; Store contents of accumulator to memory location 2610H
	   MOV B,A		 ; Copy the content of accumulator to register B
	   MVI A,08H	 ; Store 08H to register A
	   SUB B		 ; Subtract content of D register from accumulator content
	   STA 2511H	 ; Store contents of accumulator to memory location 2511H
       HLT			 ; Terminates the program
