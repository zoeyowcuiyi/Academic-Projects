import unittest
from unittest import mock

from src.bat_ui import BatUI
from src.data_mgmt import DataManager


def set_up_mock():
    mock_data_manager = DataManager()
    return BatUI(mock_data_manager)


class TestBatUI(unittest.TestCase):

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item_not_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['1', '0']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()
        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item_found_select_n(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['1', '6', 'n']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item_found_select_y_patron_not_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['1', '6', 'y', 'invalid', '0']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item_found_select_y_patron_found_cant_borrow(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['1', '6', 'y', 'John Doe', '95', '7']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_loan_item_found_selected_y_patron_found_can_borrow(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['1', '1', 'y', 'John Doe', '95', '7']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_return_item_patron_not_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['2', 'invalid', '0']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_return_item_patron_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['2', 'John Doe', '95', '0', '1']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_search_for_patron_back_to_main_menu(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['3', '3']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_search_for_patron_search_by_name_patron_not_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['3', '1', 'invalid']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("SEARCH FOR PATRON", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_search_for_patron_search_by_name_patron_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['3', '1', 'John Doe']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("SEARCH FOR PATRON", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_search_for_patron_search_by_age_patron(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['3', '2', '0']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("SEARCH FOR PATRON", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_register_patron_add_new_patron(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['4', 'Zoe Yow', '19']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_access_makerspace_patron_not_found(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['5', 'invalid', '19']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_access_makerspace_patron_found_not_allowed(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['5', 'John Doe', '95']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_access_makerspace_patron_found_allowed(self, readstr):
        mock_ui = set_up_mock()
        readstr.side_effect = ['5', 'Jane Smith', '23']
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("MAIN MENU", mock_ui.get_current_screen())

    @mock.patch('src.bat_ui.user_input.read_string')
    def test_quit_method(self, readstr):
        mock_ui = set_up_mock()
        readstr.return_value = '6'
        mock_ui.run_current_screen()
        mock_ui.run_current_screen()

        self.assertEqual("QUIT", mock_ui.get_current_screen())
