create sequence hibernate_sequence start 1 increment 1

    create table applications (
        app_id int4 not null,
        cover_letter text,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        phone_number varchar(255),
        job_id int4,
        resume_id int4,
        primary key (app_id)
    )

    create table degrees (
        degree_id int4 not null,
        education_level varchar(255),
        date_end timestamp,
        major varchar(255),
        school varchar(255),
        date_start timestamp,
        user_id int4,
        primary key (degree_id)
    )

    create table employer_reviews (
        employer_review_id int4 not null,
        employer_review varchar(255),
        employer_id int4,
        user_id int4,
        primary key (employer_review_id)
    )

    create table favorites (
        job_id int4 not null,
        user_id int4 not null
    )

    create table job_postings (
        job_id int4 not null,
        date_closed timestamp,
        date_posted timestamp,
        job_description text,
        job_title varchar(255),
        location varchar(255),
        contact_email varchar(255),
        contact_first_name varchar(255),
        contact_title varchar(255),
        contact_last_name varchar(255),
        contact_phone varchar(255),
        salary varchar(255),
        website varchar(255),
        employer_id int4,
        primary key (job_id)
    )

    create table job_reviews (
        job_review_id int4 not null,
        job_review varchar(255),
        job_id int4,
        user_id int4,
        primary key (job_review_id)
    )

    create table jobs_applied (
        job_id int4 not null,
        user_id int4 not null
    )

    create table resumes (
        resume_id int4 not null,
        file_data bytea,
        file_name varchar(255),
        name varchar(255),
        upload_date timestamp,
        user_id int4,
        primary key (resume_id)
    )

    create table user_phones (
        user_id int4 not null,
        phone varchar(255)
    )

    create table user_roles (
        user_id int4 not null,
        role varchar(255)
    )

    create table users (
        user_type varchar(31) not null,
        user_id int4 not null,
        city varchar(255),
        state varchar(255),
        street varchar(255),
        zip varchar(255),
        email varchar(255),
        enabled boolean not null,
        first_name varchar(255),
        admin boolean,
        last_name varchar(255),
        password varchar(255) not null,
        reported boolean not null,
        supress_contact boolean,
        username varchar(255) not null,
        employer_website varchar(255),
        current_job_title varchar(255),
        experience text,
        primary key (user_id)
    )

    alter table users 
        add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)

    alter table applications 
        add constraint FKskjjnjxbf1ir89jh5ts9mh0p4 
        foreign key (job_id) 
        references job_postings

    alter table applications 
        add constraint FKbhnfs5wwy14wdm2f1wam8qkhf 
        foreign key (resume_id) 
        references resumes

    alter table degrees 
        add constraint FKbl34awt7dptaxwwwifbj1sn1q 
        foreign key (user_id) 
        references users

    alter table employer_reviews 
        add constraint FKcdpvu4cr5ws90fash60xife7g 
        foreign key (employer_id) 
        references users

    alter table employer_reviews 
        add constraint FK86k1w8enbn7sra4r5p9v2pg94 
        foreign key (user_id) 
        references users

    alter table favorites 
        add constraint FKk7du8b8ewipawnnpg76d55fus 
        foreign key (user_id) 
        references users

    alter table favorites 
        add constraint FK5lfwy7d3x413b8qf89vryg3ce 
        foreign key (job_id) 
        references job_postings

    alter table job_postings 
        add constraint FKj4agtqmtm23giac4xnr0hilnx 
        foreign key (employer_id) 
        references users

    alter table job_reviews 
        add constraint FKpu8955mlgpc7mdorismea43bk 
        foreign key (job_id) 
        references job_postings

    alter table job_reviews 
        add constraint FK1kbkloahpup1tjoriwq38mfj7 
        foreign key (user_id) 
        references users

    alter table jobs_applied 
        add constraint FKa854477pp1picvjugj8t5m720 
        foreign key (user_id) 
        references users

    alter table jobs_applied 
        add constraint FK7vagh5oswjxnawgb30asknx4o 
        foreign key (job_id) 
        references job_postings

    alter table resumes 
        add constraint FK340nuaivxiy99hslr3sdydfvv 
        foreign key (user_id) 
        references users

    alter table user_phones 
        add constraint FKnup2o0u3x7dudj4ky81oiio13 
        foreign key (user_id) 
        references users

    alter table user_roles 
        add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
        foreign key (user_id) 
        references users
