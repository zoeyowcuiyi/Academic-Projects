'''
Unit tests for task 6 of assignment 1.

Reminder, the method you are testing is:
    type_of_patron(age)
Data types and descriptions are provided in the assignment specification.

You can assume that the type_of_patron method is already imported into this python module,
so you can call "type_of_patron" directly.

Author: Zoe Yow Cui Yi
Student ID: 33214476
'''
import unittest

# Boundaries:
# age == 90
# age == 18

class TestTypeOfPatron (unittest.TestCase):

    # Equivalance Partitioning 
    def test_elderly_patron(self):
        self.assertEqual(type_of_patron(100), "Elderly")

    # Boundary Value Analysis
    def test_elderly_patron_boundary(self):
        self.assertEqual(type_of_patron(90), "Elderly")

    # Equivalance Partitioning 
    def test_adult_patron(self):
        self.assertEqual(type_of_patron(55), "Adult")

    # Boundary Value Analysis
    def test_adult_patron_upper_boundary(self):
        self.assertEqual(type_of_patron(89), "Adult")

    # Boundary Value Analysis
    def test_adult_patron_lower_boundary(self):
        self.assertEqual(type_of_patron(18), "Adult")

    # Equivalance Partitioning 
    def test_minor_patron(self):
        self.assertEqual(type_of_patron(9), "Minor")

    # Boundary Value Analysis
    def test_minor_patron_upper_boundary(self):
        self.assertEqual(type_of_patron(17), "Minor")

    # Boundary Value Analysis
    def test_minor_patron_lower_boundary(self):
        self.assertEqual(type_of_patron(0), "Minor")    

    # Boundary Value Analysis
    def test_error_age_upper_boundary(self):
        self.assertEqual(type_of_patron(-1), "ERROR")
    
    # Equivalance Partitioning
    def test_error_age(self):
        self.assertEqual(type_of_patron(-9), "ERROR")
