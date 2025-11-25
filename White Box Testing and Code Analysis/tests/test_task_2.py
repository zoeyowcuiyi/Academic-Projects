import unittest

from src.business_logic import can_borrow_carpentry_tool

'''
Possible tests:
Let A be the first condition (fees_owed > 0),
B be the second condition (patron_age <= 18),
C be the third condition (patron_age >= 90)

1: A=T, B=T, C=F, Outcome=F
2: A=T, B=F, C=T, Outcome=F
3: A=T, B=F, C=F, Outcome=F
4: A=F, B=T, C=F, Outcome=F
5: A=F, B=F, C=T, Outcome=F
6: A=F, B=F, C=F, Outcome=T

Possible optimal sets of tests using MC/DC:
- 3, 4, 5, 6

Set chosen: (3, 4, 5, 6)
'''


class TestCanBorrowCarpentryTool(unittest.TestCase):
    # Test for 3: A=T, B=F, C=F, Outcome=F
    def test_t_f_f(self):
        self.assertFalse(can_borrow_carpentry_tool(45, 7, 10, True))

    # Test for 4: A=F, B=T, C=F, Outcome=F
    def test_f_t_f(self):
        self.assertFalse(can_borrow_carpentry_tool(17, 7, 0, True))

    # Test for 5: A=F, B=F, C=T, Outcome=F
    def test_f_f_t(self):
        self.assertFalse(can_borrow_carpentry_tool(90, 7, 0, True))

    # Test for 6: A=F, B=F, C=F, Outcome=T
    def test_f_f_f(self):
        self.assertTrue(can_borrow_carpentry_tool(45, 7, 0, True))
