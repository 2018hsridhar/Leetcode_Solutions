# 1741. Find Total Time Spent by Each Employee
# URL = https://leetcode.com/problems/find-total-time-spent-by-each-employee/
# Write your MySQL query statement below
SELECT event_day AS day, emp_id, SUM(out_time - in_time) AS total_time FROM Employees
GROUP BY event_day, emp_id
