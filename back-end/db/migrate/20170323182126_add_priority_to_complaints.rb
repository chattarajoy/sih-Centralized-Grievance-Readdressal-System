class AddPriorityToComplaints < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :priority, :string
  end
end
