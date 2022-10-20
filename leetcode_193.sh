# 193. Valid Phone Numbers
# URL = https://leetcode.com/problems/valid-phone-numbers/
# Read from the file file.txt and output all valid phone numbers to stdout.
# One-linear bash shell script
# Note use of ^,$ for full line matching
# https://www.thegeekstuff.com/2011/10/grep-or-and-not-operators/
# 
# grep -e "^([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]$" -e "^[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]$" < file.txt
grep "^([0-9][0-9][0-9]) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]$\|^[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]$" < file.txt
