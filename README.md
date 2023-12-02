# Advent of Code

This contains my implementations for advent of code.
Plans for this year, from day three:

Check in each "phase" separately, so I can review my own thought processes.

## 2023, Day 2

Expected to have to manipulate the expected values, too.
Was not the case. Luckily, I separated the extraction from the processing in a way that made
examining the data very simple. This made part two actually simpler, as no re-thinking was required.

## 2023, Day 1

Man this was annoying. I actually tried to examine the input by character in phase one, however that quickly became
too much work when phase two came around.
Rewrote the entire thing to work with regex-matching.
The solution is currently too complicated, maybe I will optimize later.

What was REALLY annoying was that the test data did not contain a simple `twone` or similar as input,
as this would be `21` and not `22`, requiring a change in mather processing.
