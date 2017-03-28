class CreateComplaintFeedbacks < ActiveRecord::Migration[5.0]
  def change
    create_table :complaint_feedbacks do |t|
      t.integer :complaint_id
      t.text :feedback

      t.timestamps
    end
  end
end
