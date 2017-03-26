class AddAadharNumberToUser < ActiveRecord::Migration[5.0]
  def change
    add_column :users, :aadhar_number, :string
  end
end
