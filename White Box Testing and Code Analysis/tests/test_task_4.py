import unittest
from unittest import mock

from src.bat_ui import BatUI
from src.data_mgmt import DataManager


def set_up_mock():
    mock_data_manager = DataManager()
    return BatUI(mock_data_manager)


class TestBatUIMainMenu(unittest.TestCase):

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '1'
        mock_ui.run_current_screen()
        self.assertEqual("LOAN ITEM", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_return_item(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '2'
        mock_ui.run_current_screen()
        self.assertEqual("RETURN ITEM", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_search_for_patron(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '3'
        mock_ui.run_current_screen()
        self.assertEqual("SEARCH FOR PATRON", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_register_patron(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '4'
        mock_ui.run_current_screen()
        self.assertEqual("REGISTER PATRON", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_access_makerspace(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '5'
        mock_ui.run_current_screen()
        self.assertEqual("ACCESS MAKERSPACE", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_quit(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '6'
        mock_ui.run_current_screen()
        self.assertEqual("QUIT", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_invalid_inputs(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['invalid', '8', '2']
        mock_ui.run_current_screen()
        self.assertEqual("RETURN ITEM", mock_ui.get_current_screen())
