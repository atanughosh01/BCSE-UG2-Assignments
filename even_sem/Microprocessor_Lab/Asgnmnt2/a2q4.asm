;<Store and count number of even and odd numbers from a given list of numbers>

JMP Start				; starts the execution

Start: LXI H,204FH	; taking count of numbers as input
	   MOV B,M		; storing that count in reg B
	   LXI H,2050H	; taking first input of the list of numbers
	   MVI C,00H	; initialise reg C with 00H
	   LXI D,2200H	; load reg pair [DE] with content from adress 2200H

LOOP:  MOV A,M		; move input number to accumulator A
	   RAR			; Rotate right through Carry
	   JNC SKIP		; ifNoCarryFound, jump to SKIP
	   INR C		; increment the count of odd numbers
	   RAL			; Rotate left through Carry
	   XCHG			; exchange contents of M[HL] with contents of [DE]
	   MOV M,A		; move accumulator content to M[HL]
	   XCHG			; exchange contents of M[HL] with contents of [DE]
	   INX D		; increment the reg pair address of DE by 1

SKIP:  DCR B		; decrement the count of input numbers by 1
	   INX H		; take the next input
	   JNZ LOOP		; if count isNotEqualTo 0 jump to LOOP
	   MOV A,C		; move the count of odd numbers to accumulator
	   STA 2301H	; store the oddNumberCount at address 2301H
	   LXI H,204FH	; taking count of numbers as input
	   MOV B,M		; storing that count in reg B
	   LXI H,2050H	; taking first input of the list of numbers
	   MVI C,00H	; initialise reg C with 00H
	   LXI D,2100H	; load reg pair [DE] with content from adress 2100H

LOOP1: MOV A,M		; move input number to accumulator A
	   INX H		; take the next input
	   RAR			; Rotate right through Carry
	   JC SKIP1		; ifCarryFound, jump to SKIP1
	   INR C		; increment the count of even numbers
	   RAL			; Rotate left through Carry
	   XCHG			; exchange contents of M[HL] with contents of [DE]
	   MOV M,A		; move accumulator content to M[HL]
	   XCHG			; exchange contents of M[HL] with contents of [DE]
	   INX D		; increment the reg pair address of DE by 1

SKIP1: DCR B		; decrement the count of input numbers by 1
	   JNZ LOOP1	; if count isNotEqualTo 0 jump to LOOP1
	   MOV A,C		; move the count of even numbers to accumulator
	   STA 2300H	; store the evenNumberCount at address 2301H
	   HLT			; terminates the execution
	   
