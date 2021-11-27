;<Program title>

JMP Start              ; Jump to START

Start: LDA 2200H       ; Load accumulator with content of location 2200H
       MOV B,A         ; Copy content of accumalator to register B / Setting the Count(N)
       LXI H,2500H     ; Store 25H to H register and 00H to L register
       MVI A,00H       ; Store 00H to accumulator / Initializing Sum
       MVI C,00H       ; Store 00H to register C / Initializing Carry
       
Skip:  ADD M           ; Add content of accumulator with contents of memory location from H-L register pair
	INX H   	  ; Increase the address of H-L register pair by 1
       JNC Loop        ; If no carry, enter the LOOP
       INR C	         ; Increase the content of register C by 1 / Increment the Carry(C = C+1)
       
Loop:  DCR B           ; Decrease the content of register B by 1 / Decrement the Count(N = N-1)
	JNZ Skip        ; If not zero, jump to SKIP
	STA 2300H       ; Store contents of accumulator to memory location 2300H
       MOV A,C         ; Copy contents of register C to accumulator / Store the Carry
       STA 2301H       ; Store contents of accumulator to memory location 2301H
       HLT		  ; Terminates the program
