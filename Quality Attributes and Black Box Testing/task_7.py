'''
Unit tests for task 7 of assignment 1.

Reminder, the method you are testing is:
    can_borrow(item_type, patron_age, length_of_loan, outstanding_fees, gardening_training, carpentry_training)
Data types and descriptions are provided in the assignment specification.

You can assume that the can_borrow method is already imported into this python module,
so you can call "can_borrow" directly.

Author: Zoe Yow Cui Yi
Student ID: 33214476
'''

import unittest

class TestCanBorrow(unittest.TestCase):
    def test_gardening_minor_short(self):
        self.assertFalse(can_borrow("Gardening", 10, 1, 10.5, True, False))

    def test_carpentry_minor_long(self):
        self.assertFalse(can_borrow("Carpentry", 17, 14, 0, False, True))

    def test_book_elderly_medium(self):
        self.assertFalse(can_borrow("Book", 100, 5, 20, True, True))

    def test_book_adult_medium(self):
        self.assertTrue(can_borrow("Book", 55, 7, 0, False, False))

    def test_book_elderly_long(self):
        self.assertTrue(can_borrow("Book", 95, 10, 0, True, False))

    def test_gardening_adult_short(self):
        self.assertFalse(can_borrow("Gardening", 45, 1, 9.4, False, True))

    def test_gardening_elderly_long(self):
        self.assertFalse(can_borrow("Gardening", 95, 10, 0, False, True))

    def test_book_elderly_short(self):
        self.assertTrue(can_borrow("Book", 95, 2, 0, True, True))

    def test_gardening_minor_medium(self):
        self.assertTrue(can_borrow("Gardening", 5, 5, 0, True, True))

    def test_carpentry_elderly_medium(self):
        self.assertFalse(can_borrow("Carpentry", 100, 7, 34.5, True, False))

    def test_carpentry_adult_long(self):
        self.assertFalse(can_borrow("Carpentry", 55, 14, 5.5, True, False))

    def test_carpentry_minor_short(self):
        self.assertFalse(can_borrow("Carpentry", 2, 12.3, False, False))

    def test_book_minor_medium(self):
        self.assertTrue(can_borrow("Book", 10, 7, 0, True, True))