# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170328195212) do

  create_table "aadhars", force: :cascade do |t|
    t.string   "uid"
    t.string   "name"
    t.string   "phone"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "active_complaints", force: :cascade do |t|
    t.integer  "ward_office_id"
    t.integer  "complaint_id"
    t.datetime "created_at",         null: false
    t.datetime "updated_at",         null: false
    t.integer  "district_office_id"
  end

  create_table "admin_users", force: :cascade do |t|
    t.string   "name"
    t.string   "email"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
    t.string   "password_digest"
    t.string   "phone"
    t.string   "designation"
    t.string   "municipal_id"
  end

  create_table "api_keys", force: :cascade do |t|
    t.string   "secret_key"
    t.integer  "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string   "user_token"
    t.string   "user_type"
  end

  create_table "complaint_feedbacks", force: :cascade do |t|
    t.integer  "complaint_id"
    t.text     "feedback"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
  end

  create_table "complaint_updates", force: :cascade do |t|
    t.integer  "complaint_id"
    t.string   "assigned_to"
    t.text     "notes"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
  end

  create_table "complaints", force: :cascade do |t|
    t.string   "subject"
    t.text     "description"
    t.text     "image"
    t.decimal  "latitude"
    t.decimal  "longitude"
    t.string   "district"
    t.string   "state"
    t.integer  "pincode"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
    t.integer  "user_id"
    t.string   "status"
    t.string   "priority"
    t.string   "address"
  end

  create_table "district_offices", force: :cascade do |t|
    t.string   "state"
    t.string   "district"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "emails", force: :cascade do |t|
    t.string   "user_id"
    t.string   "verify_token"
    t.string   "user_token"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
  end

  create_table "new_complaints", force: :cascade do |t|
    t.integer  "complaint_id"
    t.integer  "district_office_id"
    t.datetime "created_at",         null: false
    t.datetime "updated_at",         null: false
  end

  create_table "password_reset_links", force: :cascade do |t|
    t.integer  "user_id"
    t.string   "access_token"
    t.string   "secret_key"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
  end

  create_table "resolvedcomplaints", force: :cascade do |t|
    t.integer  "complaint_id"
    t.integer  "districtoffice_id"
    t.integer  "wardoffice_id"
    t.datetime "created_at",        null: false
    t.datetime "updated_at",        null: false
  end

  create_table "sms_otps", force: :cascade do |t|
    t.string   "user_id"
    t.string   "otp"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "users", force: :cascade do |t|
    t.string   "name"
    t.string   "email"
    t.boolean  "phone_no_verified"
    t.boolean  "aadhar_verified"
    t.datetime "created_at",        null: false
    t.datetime "updated_at",        null: false
    t.string   "password_digest"
    t.string   "contact"
    t.boolean  "email_verified"
    t.string   "aadhar_number"
  end

  create_table "ward_offices", force: :cascade do |t|
    t.integer  "district_office_id"
    t.string   "ward"
    t.datetime "created_at",         null: false
    t.datetime "updated_at",         null: false
  end

end
