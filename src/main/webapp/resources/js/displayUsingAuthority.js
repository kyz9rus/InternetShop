// var data = [];
//
// $.ajax({
//     type : "GET",
//     contentType : "application/json",
//     url : "getAuthority",
//     data : JSON.stringify(data),
//     dataType : 'json',
//     timeout : 100000,
//     success : function(data) {
//         var role = data.roles[0].name;
//
//         if (role === "EMPLOYEE") {
//             $('.loginHeaderBlock img').attr('src', '/resources/images/logout.png');
//             $('.loginHeaderBlock a').attr('href', '/logout');
//
//             $('.registrationHeaderBlock img').attr('src', '/resources/images/profile.png');
//             $('.registrationHeaderBlock a').attr('href', '/employeeProfile');
//
//             $('.lastBlock #login').attr('src', '/resources/images/logout.png');
//             $('.lastBlock a').attr('href', '/logout');
//
//             $('.mobileMenu .register').text('Profile');
//             $('.mobileMenu .register').attr('href', '/employeeProfile');
//         } else if (role === "CLIENT") {
//             $('.loginHeaderBlock img').attr('src', '/resources/images/logout.png');
//             $('.loginHeaderBlock a').attr('href', '/logout');
//
//             $('.registrationHeaderBlock img').attr('src', '/resources/images/profile.png');
//             $('.registrationHeaderBlock a').attr('href', '/clientProfile');
//
//             $('.lastBlock #login').attr('src', '/resources/images/logout.png');
//             $('.lastBlock a').attr('href', '/logout');
//
//             $('.mobileMenu .register').text('Profile');
//             $('.mobileMenu .register').attr('href', '/clientProfile');
//         } else if (role === "anonymousUser") {
//
//         } else
//             console.log('strange role...');
//     }
// });