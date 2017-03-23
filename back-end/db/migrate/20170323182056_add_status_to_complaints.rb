class AddStatusToComplaints < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :status, :string
  end
end
