import unittest

from src.business_logic import can_use_makerspace


'''
Feasible paths:
1: 135->148->150->151->153->154->161->164
2: 135->148->150->151->153->155->156->161->164
3: 135->148->150->151->153->155->157->158->159->161->162->164
4: 135->148->150->151->153->155->157->158->159->161->164
'''


class TestCanUseMakerspace(unittest.TestCase):
    # Test for 1: 135->148->150->151->153->154->161->164
    def test_error(self):
        self.assertFalse(can_use_makerspace(-10, 10, True))

    # Test for 2: 135->148->150->151->153->155->156->161->164
    def test_elderly_or_minor(self):
        self.assertFalse(can_use_makerspace(90, 10, True))

    # Test for 3: 135->148->150->151->153->155->157->158->159->161->162->164
    def test_adult_fees_owed_with_makerspace_training(self):
        self.assertFalse(can_use_makerspace(50, 10, True))

    # Test for 4: 135->148->150->151->153->155->157->158->159->161->164
    def test_adult_fees_owed_without_makerspace_training(self):
        self.assertFalse(can_use_makerspace(50, 10, False))
