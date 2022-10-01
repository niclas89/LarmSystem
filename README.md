# LarmSystem
Larmsystem Uppgift OOP

openjdk-18 

Ljudvarning , högt ljud på larmsingnalerna sänk ljudet innan du startar programmet

För att få ljudfilerna att funka när jag laddade ner programmet på en annan dator var jag tvungen att ändra path för filerna , 
Copy/Path reference , Path from Repository Root;
Då fungerade ljudfilerna för mig på andra datorn.


För att aktivera larmet skriver du koden 2478 och avslutar med # samma förfarande för att avaktivera eller stänga av om larmet gått igång.
Du styr inbrottstjuven med wasd, om larmet är på och du kolliderar med en dörr/fönster/rörelsedektor så kommer motsvarande dörr/fönster öppnas eller rörelsedetektor aktiveras,om det är någon av ytterdörarna kommer du dock ha 15 sekunder på dig att larma av innan larmet går såvida du inte påverkar någon annan detektor.

Du simulerar brand genom drop down meny och återställer genom knappen eller genom att skriva in larmkoden
 , sprinkler aktiveras om man inte stängar av brandvarnaren inom 10sek

Alla händelser presenteras i logg terminalen , man rensar loggen genom clear knappen.

Jag visst inte hur jag skulle  simulera väggarna till alla rum i JavaFX utan en väldig massa fx-object så väggarna finns inte utan endast  dörrar/fönster/detektorer.
