class AddAddressToComplaint < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :address, :string
  end
end
