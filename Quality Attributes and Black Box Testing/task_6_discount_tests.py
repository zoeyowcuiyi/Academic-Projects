'''
Unit tests for task 6 of assignment 1.

Reminder, the method you are testing is:
    calculate_discount(age)
Data types and descriptions are provided in the assignment specification.

You can assume that the calculate_discount method is already imported into this python module,
so you can call "calculate_discount" directly.

Author: Zoe Yow Cui Yi
Student ID: 33214476
'''
import unittest

# Boundaries:
# age == 50
# age == 65
# age == 90

class TestCalculateDiscount(unittest.TestCase):
    # Equivalance Partitioning
    def test_age_50_or_less(self):
        self.assertEqual(calculate_discount(25), 0)

    # Boundary Value Analysis
    def test_age_50_or_less_upper_boundary(self):
        self.assertEqual(calculate_discount(50), 0)

    # Boundary Value Analysis
    def test_age_between_51_and_64_lower_boundary(self):
        self.assertEqual(calculate_discount(51), 10)

    # Equivalance Partitioning
    def test_age_between_51_and_64(self):
        self.assertEqual(calculate_discount(60), 10)

    # Boundary Value Analysis
    def test_age_between_51_and_64_upper_boundary(self):
        self.assertEqual(calculate_discount(64), 10)

    # Boundary Value Analysis
    def test_age_between_65_and_89_lower_boundary(self):
        self.assertEqual(calculate_discount(65), 15)

    # Equivalance Partitioning
    def test_age_between_65_and_89(self):
        self.assertEqual(calculate_discount(75), 15)

    # Boundary Value Analysis
    def test_age_between_65_and_89_upper_boundary(self):
        self.assertEqual(calculate_discount(89), 15)

    # Boundary Value Analysis
    def test_age_90_or_more_lower_boundary(self):
        self.assertEqual(calculate_discount(90), 100)

    # Equivalance Partitioning
    def test_age_90_or_more(self):
        self.assertEqual(calculate_discount(100), 100)

    # Boundary Value Analysis
    def test_error_discount_upper_boundary(self):
        self.assertEqual(calculate_discount(-1), "ERROR")

    # Equivalance Partitioning
    def test_error_discount(self):
        self.assertEqual(calculate_discount(-10), "ERROR")