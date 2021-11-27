;<Overlap certain numbers>

JMP Start			; starts the execution

Start:	LXI H,201EH		; taking N as input
        MOV B,M 		; store N at reg B
        LXI H,201FH     	; taking M as input
        MOV C,M     	        ; store M at reg C
        MOV A,B			; move value of N to accumulator
        SUB C			; subtract M from N and store in accumulator
        LXI H,2050H		; take input of list
        ADD L			; add (A <-- A + L)
        JNC Skip		; if not 0 jump to Skip
        INX H			; take next input
        
Skip:	MOV L,A			; move accumulator content to reg L
	MOV C,B			; move value of N to reg C
        MVI B,00H		; initialise reg B with 00H
        DCX B			; decrement the value of N by 1
        DAD B			; direct addition
        XCHG			; exchange content between HL and DE pair
        LXI H,2050H		; take next input
        DAD B			; direct addition
        INR B			; increment value of N
        
LOOP:	MOV A,M			; store input in accumulator
	XCHG			; exchange content between HL and DE pair
        MOV M,A			; swap the reg contents
        DCX D			; decrement value of D
        DCX H			; decrement address of HL pair
        XCHG			; exchange content between HL and DE pair
        DCR C			; decrement value of N
        JNZ LOOP		; if N isNotEqualTo 0 jump to LOOP
       
        HLT			; terminates the execution
