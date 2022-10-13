#2356. Number of Unique Subjects Taught by Each Teacher
# URL = https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/
# Write your MySQL query statement below
# Can we even use the DISTINCT keyword here?
# Introduce use of alias here
# Notice usage of count and then group_by later ( to partition aggrs ) 
SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt FROM Teacher
GROUP BY teacher_id
