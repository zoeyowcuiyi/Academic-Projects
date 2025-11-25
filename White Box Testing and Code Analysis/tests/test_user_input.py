import unittest
from unittest import mock

from src.user_input import *


class TestUserInput(unittest.TestCase):
    def test_is_float_true(self):
        self.assertTrue(is_float(12.3))

    def test_is_float_false(self):
        self.assertFalse(is_float('invalid'))

    @mock.patch('builtins.input')
    def test_read_string(self, readstr):
        readstr.return_value = "String"
        self.assertEqual("String", read_string("Enter a value: "))

    @mock.patch('src.user_input.read_string')
    def test_read_float(self, readstr):
        readstr.side_effect = ["invalid", "12.3"]
        self.assertEqual(12.3, read_float("Enter a value: "))

    @mock.patch('src.user_input.read_string')
    def test_read_float_string(self, readstr):
        readstr.side_effect = ["15", "12.3"]
        self.assertEqual(12.3, read_float_range("Enter a value: ", 11, 13))

    @mock.patch('src.user_input.read_string')
    def test_read_bool(self, readstr):
        readstr.side_effect = ['invalid', 'y']
        self.assertEqual('y', read_bool("y or n: "))
