import unittest

from src.business_logic import *


class TestBusinessLogic(unittest.TestCase):
    def test_type_of_patron_minor(self):
        self.assertEqual("Minor", type_of_patron(17))

    def test_can_borrow_for_book(self):
        self.assertTrue(can_borrow("Book", 18, 7, 0, True, True))

    def test_can_borrow_for_gardening_tool(self):
        self.assertTrue(can_borrow("Gardening tool", 18, 7, 0, True, True))

    def test_can_borrow_for_carpentry_tool(self):
        self.assertTrue(can_borrow("Carpentry tool", 19, 7, 0, True, True))

    def test_can_borrow_for_invalid(self):
        self.assertFalse(can_borrow("Invalid", 18, 7, 0, True, True))

    def test_can_borrow_book_length_of_loan_more_than_56(self):
        self.assertFalse(can_borrow_book(18, 56, 0))

    def test_can_borrow_book_has_fees_owed(self):
        self.assertFalse(can_borrow_book(18, 7, 10))

    def test_can_borrow_gardening_tool_has_fees_owed(self):
        self.assertFalse(can_borrow_gardening_tool(18, 7, 10, True))

    def test_can_borrow_gardening_tool_length_of_loan_above_28(self):
        self.assertFalse(can_borrow_gardening_tool(18, 29, 0, True))

    def test_can_borrow_carpentry_tool_has_fees_owed(self):
        self.assertFalse(can_borrow_gardening_tool(19, 7, 10, True))

    def test_can_borrow_carpentry_tool_length_of_loan_above_14(self):
        self.assertFalse(can_borrow_carpentry_tool(19, 15, 0, True))

    def test_calculate_discount_invalind_age(self):
        self.assertEqual("ERROR", calculate_discount(-1))

    def test_calculate_discount_65_and_over_and_under_90(self):
        self.assertEqual(15, calculate_discount(70))

    def test_calculate_discount_90_and_over(self):
        self.assertEqual(100, calculate_discount(90))
