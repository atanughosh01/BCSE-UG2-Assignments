;<Store those numbers whose sum of LSB and MSB are 1 at certain positions>

JMP Start				; starts the execution

Start:	LXI H,21FFH		; taking the count of numbers as input
		MOV B,M			; move the count to reg B
		LXI H,2200H		; taking the first input of numbers
		MVI C,00H		; initialising C reg
		MVI A,00H		; initialising A reg
	
LOOP:	STA 2600H		; store accumulator content at address 2600H
		MOV A,M			; number taken as input is moved to Accumulator
		ANI 81H			; 1000 0001 ANDed
		CPI 81H			; check if LSB=1 and MSB=1
		JNZ SKIP1		; if LSB AND MSB not = 1 then goto SKIP1
		LDA 2600H		; stores the existing sum
		ADD M			; add the new number to sum
		JNC SKIP		; if no carry found, jump to SKIP
		INR C			; increment reg C content
		JMP SKIP		; unconditionally jump to SKIP
	
SKIP1:	LDA 2600H		; load accumulator from address 2600H
		INX H			; increment M [HL]
		DCR B			; decrement the count by 1
		JNZ LOOP		; if count isNotEqualTo 0, jump to LOOP
		JMP LAST		; unconditionally jump to LAST
	
SKIP:	INX H			; increment M [HL]
		DCR B			; decrement the count by 1
		JNZ LOOP		; if count isNotEqualTo 0, jump to LOOP

LAST:	STA 2500H		; store the count of numbers with SumOfLSB = 1 at address 2500H
		MOV A,C			; move the count of numbers having SumOfMSB = 1 to accumulator
		STA 2501H		; store the count of numbers with SumOfMSB = 1 at address 2501H
		HLT				; terminates the execution
